function TopMenu() {
    this.load = function() {

        topHtml = '<a class="navbar-brand" href="/index.html">Navbar</a>\n' +
            '    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">\n' +
            '        <span class="navbar-toggler-icon"></span>\n' +
            '    </button>\n' +
            '    <div class="collapse navbar-collapse" id="collapsibleNavbar">\n' +
            '        <ul class="navbar-nav">\n' +
            '            <li class="nav-item">\n' +
            '                <a class="nav-link" href="/index.html">Main</a>\n' +
            '            </li>\n' +
            '        </ul>\n' +
            '    </div>';
        $('.navbar').append(topHtml);


    }
}