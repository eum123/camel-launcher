var HOST = "http://127.0.0.1:7070";

function api_get(path, successFunction, failFunction) {
    $.ajax(HOST + path,
        {
            dataType: 'json',	//결과를 json으로 받아옴
            type: 'get',

            success: function (data) {

                console.log(data);

                if (data.result_code == 'OK') {
                    successFunction(data.result);
                } else {
                    failFunction;
                }

            },
            error: function () {
                failFunction;
            }
        });
}
