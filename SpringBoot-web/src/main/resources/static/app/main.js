require.config({
    paths:{
        'velocity': '/resources/js/velocity/velocity.min',
        'vue-router': 'resources/js/vue-router/vue-router.min',
        "jquery":'/resources/js/jquery/3.2.1/jquery.slim.min',
        "popper": '/resources/js/popper/popper.min',
        "bootstrap": '/resources/js/bootstrap/js/bootstrap.min',
        'vue': '/resources/js/vue/vue',
        'axios': '/resources/js/axios/axios.min',
        'bootstrap-vue': '/resources/js/bootstrap-vue/bootstrap-vue.min'
    },
    map: {
        '*': {
            'popper.js': 'popper'
        }
    }
});
require(['popper','bootstrap', 'axios'],function(){
    require(['vue', 'bootstrap-vue', 'vue-router'], function (vue, bootstrapVue, VueRouter) {
        vue.use(bootstrapVue);
        vue.use(VueRouter);
        require(['/app/component/menu.js','/app/app.js'], function() {
            console.log('loaded')
        });
    });
});