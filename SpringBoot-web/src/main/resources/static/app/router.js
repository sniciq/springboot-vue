define(function(require) {
    var VueRouter = require("vue-router");
    var Vue = require("vue");
    var Foo = { template: '<div>foo</div>' };
    var Bar = { template: '<div>bar</div>' };

    var routes = [
        { path: '/', component: Vue.component('home', function (resolve, reject) {
                require(['/app/component/home.js'], resolve);
            })
        },
        {
            path: '/module/staff', component: Vue.component('staff', function (resolve, reject) {
                require(['/app/component/staff.js'], resolve);
            })
        }
    ];

    var router = new VueRouter({
        routes: routes
    });
    router.afterEach((to, from) => {
        this.$emit('afterRoute', to)
    });

    return router;
});
