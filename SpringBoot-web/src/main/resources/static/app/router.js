define(function(require) {
    var VueRouter = require("vue-router");
    var Vue = require("vue");
    var Foo = { template: '<div>foo</div>' };
    var Bar = { template: '<div>bar</div>' };

    var routes = [
        { path: '/foo', component: Foo },
        { path: '/bar', component: Bar },
        {
            path: '/module/staff', component: Vue.component('staff', function (resolve, reject) {
                require(['/app/component/staff.js'], resolve);
            })
        }
    ];

    return new VueRouter({
        routes: routes
    });
});
