const COMPONENT_INFO = {
    "timer": {
        "basic": [
            {"name": "period", "default": "1000"},
            {"name": "delay", "default": "0"},
            {"name": "repeatCount", "default": "0"}
        ],
        "extension": [
            {"name": "time", "default": "null"},
            {"name": "pattern", "default": "null"},
            {"name": "fixedRate", "default": "false"},
            {"name": "daemon", "default": "true"}
        ]
    }
};

function From(value) {
    this.display = function (divName, activeKey) {

        var ul = $("<ul/>", {
            class: "nav nav-tabs"
        });

        var content = $("<div />", {
            id: "properties-content",
            class: "tab-content"
        })

        var uriJson = parseUri(value.uri);

        Object.keys(COMPONENT_INFO[uriJson.protocol]).forEach(function (key, index, array) {

            var li = $("<li/>", {
                class: "nav-item"
            });

            //nav tab
            var active = key == activeKey ? "active" : "";

            var a = $('<a class="nav-link ' + active + '" id="' + key + '-tab" data-toggle="tab" role="tab" aria-controls="' + key + '" aria-selected="true" href="#' + key + '" />');
            a.text(key);

            a.appendTo(li);
            li.appendTo(ul);

            // content
            var tabContent = $('<div id="' + key + '" class="tab-pane fade ' + active.replace("active", "show active") + '" role="tabpanel" aria-labelledby="' + key + '-tab" />');

            COMPONENT_INFO[uriJson.protocol][key].forEach(function (v, i, a) {
                var formGroup = $('<div id="' + key + '" class="input-group mb-3"/>');

                var label = $('<label for="' + v.name + '">' + v.name + ' : </label>');
                var input = $('<input type="text" class="form-control" id="' + v.name + '" value="' + getValue(uriJson['parameter'][v.name], v.default) + '">');

                label.appendTo(formGroup);
                input.appendTo(formGroup);

                formGroup.appendTo(tabContent);
            });

            tabContent.appendTo(content);

        });

        $("#" + divName).children().remove();

        ul.appendTo($("#" + divName));
        content.appendTo($("#" + divName));
    }
}

function To(value) {
    From(value);
}

function Log(value) {
    this.display = function (divName, activeKey) {

        var ul = $("<ul/>", {
            class: "nav nav-tabs"
        });

        var content = $("<div />", {
            id: "properties-content",
            class: "tab-content"
        })


        var li = $("<li/>", {
            class: "nav-item"
        });

        //nav tab

        var a = $('<a class="nav-link basic active" id="basic-tab" data-toggle="tab" role="tab" aria-controls="basic" aria-selected="true" href="#basic" />');
        a.text("basic");

        a.appendTo(li);
        li.appendTo(ul);

        // content
        var tabContent = $('<div id="basic" class="tab-pane fade show active" role="tabpanel" aria-labelledby="basic-tab" />');

        var formGroup = $('<div id="basic-group" class="input-group mb-3"/>');

        var label = $('<label for="message">message : </label>');
        var input = $('<input type="text" class="form-control" id="message" value="' + value.message + '">');

        label.appendTo(formGroup);
        input.appendTo(formGroup);

        formGroup.appendTo(tabContent);


        tabContent.appendTo(content);


        $("#" + divName).children().remove();

        ul.appendTo($("#" + divName));
        content.appendTo($("#" + divName));
    }
}

