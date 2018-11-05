/**
 * Route 내용조회 및 출력
 * @param ulId
 * @constructor
 */
function Route(fileName) {

    var routeData = JSON.parse('{"result_code":"OK","result_message":"정상","result":{"path":"file:///work/project/camel-launcher/src/test/resources/conf/route/process.xml","modifiyTime":1534218308000,"routes":{"routesId":"camel","routes":[{"tagName":"route","id":"process","autoStartup":true,"list":[{"tagName":"description","description":"description.... 처음에 있어야함"},{"tagName":"from","uri":"timer:hello?period=5000"},{"tagName":"log","message":"Message111:${body}"},{"tagName":"process","ref":null},{"tagName":"to","uri":"log:cool"}]}]}}}');



    this.load = function() {
        var parameter = JSON.parse('{"fileName": "' + fileName + '"}');
        api_get("/routeFiles", parameter,
            function (data) {



            },
            function() {
                //$('#' + ulId).append('데이터 조회 오류 (/routeFiles)');
            }
        );
    }

    this.display = function(divName) {

        $('#' + divName).empty();

        var routesId = routeData.result.routes.routeId;
        var routeList = routeData.result.routes.routes;

        routeList.forEach(function (value, index, array) {
            var routeId = value.id;
            var list = value.list;

            var routeDivElement = $('<div id="routeDiv_' + routeId + '">');

            list.forEach(function (value, index, array){

                var styleString = "";
                if(index == 0 || index == (array.length -1 )) {
                    styleString = 'style="opacity: 1;"'
                }
                var divElement = $('<div class="column" draggable="true" ' + styleString + ' />', {
                    class: 'column',
                     draggable: true,
                    'dragstart': function(e) {
                        console.log(e);
                        //this.handleDragStart(e);
                    },
                    dragenter: function(e) {
                        this.handleDragEnter(e);
                    },
                    dragover: function(e) {
                        this.handleDragOver(e);
                    },
                    dragleave: function(e) {
                        this.handleDragLeave(e);
                    },
                    drop: function(e) {
                        this.handleDrop(e);
                    },
                    'dragend': function(e) {
                        console.log(e);
                        //this.handleDragEnd(e);
                    }
                });


                var headerElement = $('<header/>', {
                    text: value.tagName
                });

                headerElement.appendTo(divElement);

                divElement.appendTo(routeDivElement);

                divElement.get(0).addEventListener('dragstart', this.handleDragStart);
            });

            var addElement = $('<div />', {
                class: 'add',
                click: function(e) {
                    this.addNewComponent(e);
                }
            });

            // var addHeaderElement = $('<header>Unkonwn</header>');
            // addHeaderElement.appendTo(addElement);
            addElement.appendTo(routeDivElement);

            routeDivElement.appendTo($('#' + divName));
        });


        //this.initEvent();
    }

    // Using this polyfill for safety.
    Element.prototype.hasClassName = function (name) {
        return new RegExp("(?:^|\\s+)" + name + "(?:\\s+|$)").test(this.className);
    };

    Element.prototype.addClassName = function (name) {
        if (!this.hasClassName(name)) {
            this.className = this.className ? [this.className, name].join(' ') : name;
        }
    };

    Element.prototype.removeClassName = function (name) {
        if (this.hasClassName(name)) {
            var c = this.className;
            this.className = c.replace(new RegExp("(?:^|\\s+)" + name + "(?:\\s+|$)", "g"), "");
        }
    };

    // this.initEvent = function() {
    //
    //     var id_ = 'routeDiv_process';
    //     var cols_ = document.querySelectorAll('#' + id_ + ' .column');
    //
    //     [].forEach.call(cols_, function (col) {
    //         col.setAttribute('draggable', 'true');  // Enable columns to be draggable.
    //         col.addEventListener('dragstart', this.handleDragStart, false);
    //         col.addEventListener('dragenter', this.handleDragEnter, false);
    //         col.addEventListener('dragover', this.handleDragOver, false);
    //         col.addEventListener('dragleave', this.handleDragLeave, false);
    //         col.addEventListener('drop', this.handleDrop, false);
    //         col.addEventListener('dragend', this.handleDragEnd, false);
    //
    //     });
    //
    //     var add_ = document.querySelectorAll('div .add');
    //     [].forEach.call(add_, function (col) {
    //         col.addEventListener('click', this.handleClick, false);
    //     });
    // }

    var dragSrcEl_ = null;

    Element.prototype.handleDragStart = function (e) {
        console.log(e);
        e.dataTransfer.effectAllowed = 'move';
        e.dataTransfer.setData('text/html', this.innerHTML);

        dragSrcEl_ = this;

        // this/e.target is the source node.
        this.addClassName('moving');
    };

    Element.prototype.handleDragOver = function (e) {
        if (e.preventDefault) {
            e.preventDefault(); // Allows us to drop.
        }

        e.dataTransfer.dropEffect = 'move';

        return false;
    };

    Element.prototype.handleDragEnter = function (e) {
        this.addClassName('over');
    };

    Element.prototype.handleDragLeave = function (e) {
        // this/e.target is previous target element.
        this.removeClassName('over');
    };

    Element.prototype.handleDrop = function (e) {
        // this/e.target is current target element.

        if (e.stopPropagation) {
            e.stopPropagation(); // stops the browser from redirecting.
        }

        // Don't do anything if we're dropping on the same column we're dragging.
        if (dragSrcEl_ != this) {
            // 반복
            dragSrcEl_.innerHTML = this.innerHTML;
            this.innerHTML = e.dataTransfer.getData('text/html');

            // Set number of times the column has been moved.
            // var count = this.querySelector('.count');
            // var newCount = parseInt(count.getAttribute('data-col-moves')) + 1;
            // count.setAttribute('data-col-moves', newCount);
            // count.textContent = 'moves: ' + newCount;


        }

        return false;
    };

    Element.prototype.handleDragEnd = function (e) {

        // this/e.target is the source node.
        [].forEach.call(cols_, function (col) {
            col.removeClassName('over');
            col.removeClassName('moving');
        });

    };


    /**
     * 새로운 component 추가
     * @param e
     */
    Element.prototype.addNewComponent = function (e) {
        console.log(e);

        //style="opacity: 1;
        // append unkown div
        // var divElement = $('<div class="column" draggable="true" />');
        //
        // var headerElement = $('<header>Unkown</header>');
        // headerElement.appendTo(divElement);
        //
        // divElement.appendTo(this.parentNode);
        //
        // //append 'add' div
        // var addElement = $('<div class="add"  />');
        // addElement.appendTo(this.parentNode);
        //
        // //remove add
        // this.parentNode.removeChild(this);
        //
        // //event refresh
        // this.initEvent();

    };


}