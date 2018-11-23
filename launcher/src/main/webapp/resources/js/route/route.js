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

            var panel = $('<div />', {
                class: 'card'
            });

            var panelBody = $('<div/>', {
               class: 'card-body content'
            });
            var routeArea = $('<div/>', {
                id: routeId
            })

            list.forEach(function (value, index, array){
                if(value.tagName == "description") {
                    var cardHeader = $('<div />', {
                        class: 'card-header'
                    });

                    addRouteTitle(cardHeader, routeId, value.description);
                    cardHeader.appendTo(panel);

                } else {
                    createNode(routeId, value.tagName, value).appendTo(routeArea);
                }
            });

            routeArea.appendTo(panelBody);

            panelBody.appendTo(panel);

            var panelPropertiesBody = $('<div/>', {
                class: 'card-body content'
            });

            //설정 정보 저장
            var properties = $('<div />', {
                id: routeId + "-properties"
            })
            properties.appendTo(panelPropertiesBody);
            panelPropertiesBody.appendTo(panel);

            createSaveButton(routeId).appendTo(panel);

            //nodes
            panel.appendTo($('#' + divName));

            $("#" + routeId).sortable({
                revert: true
            });

        });

    }

    function addRouteTitle(cardHeader, routeId, description) {
        var div = $('<div/>', {
            class: "row"
        })

        //설명
        var descriptionDiv = $('<div/>', {
            class: "col-sm-10",
            text: "[" + routeId + "] - " + description
        });
        descriptionDiv.css('text-align', 'left');

        //추가 button
        var buttonDiv = $('<div/>', {
            class: "col-sm-2"
        });
        buttonDiv.css('text-align', 'right');
        var button = $('<button/>', {
            type: "button",
            class: "btn btn-outline-primary btn-sm",
            text: "ADD",
            click: function(e) {
                addNewNode(routeId, e);
            }
        })
        button.appendTo(buttonDiv);


        descriptionDiv.appendTo(div);
        buttonDiv.appendTo(div);

        div.appendTo(cardHeader);
    }


    function createNode(routeId, name, value) {
        var div = $('<div />', {
            class: 'column',
            click: function(e) {displayConfig(routeId, name, value, e)}
        });

        var header = $('<header/>', {
            text: name
        });
        header.appendTo(div);

        return div;
    }

    /**
     * 새로운 node 추가
     * @param e
     */
    function addNewNode (routeId, e) {

        createNode(routeId, "Unkown").appendTo($('#' + routeId));

    };

    function displayConfig(routeId, name, value, e) {

        var node;
        if(name == 'from') {
            node = new From(value);
        } else if(name == 'log') {
            node = new Log(value);
        }
        node.display(routeId +'-properties', 'basic');

    }

    function createSaveButton(routeId) {
        var cardBody = $('<div/>', {
            class: 'card-body content'
        });
        var save_button = $('<button type="button" class="btn btn-primary btn-block">Save</button>');

        save_button.appendTo(cardBody);

        return cardBody;
    }
}