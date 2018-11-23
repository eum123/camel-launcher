/**
 *
 * @param ulId 메뉴를 출력할 ul tag의 id
 * @constructor
 */
function LeftMenu(ulId) {


    this.load = function() {
        api_get("/routeFiles", null,
            function (data) {

                data.forEach(function (value, index, array) {
                    $('#' + ulId).append('<li class="nav-item"><a class="nav-link" href="/route.html?fileName=' + value + '">' + value + '</a></li>');
                });

            },
            function() {
                $('#' + ulId).append('데이터 조회 오류 (/routeFiles)');
            }
        );
    }
}