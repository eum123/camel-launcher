function parameter(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
        return null;
    }
    else{
        return results[1] || 0;
    }
}

function parseUri(uri) {

    var json = {};

    var protocol;
    var dummy;
    if(uri.indexOf("://") < 0) {
        protocol = uri.substring(0, uri.indexOf(":"));
        dummy = uri.substring(uri.indexOf(":") + 1);
    } else {
        protocol = uri.substring(0, uri.indexOf("://"));
        dummy = uri.substring(uri.indexOf("://") + 3);
    }

    var host = dummy.substring(0, dummy.indexOf("?"));

    var parameter = dummy.substring(dummy.indexOf("?") + 1);

    json["protocol"] = protocol;
    json["host"] = host;
    json["parameter"] = parseParameter(parameter);

    return json;
}

function parseParameter(parameter) {
    var json = {};
    parameter.split("&").forEach(function (value, index, array){

        var data = value.split("=");

        json[data[0]] = data.length > 1 ? data[1] : "";
    });

    return json;
}

/**
 * 값이 없으면 default값으로 반환한다.
 * @param value
 * @param defaultValue
 * @returns {*}
 */
function getValue(value, defaultValue) {
    if(value == null) {
        return defaultValue;
    } else {
        return value;
    }
}