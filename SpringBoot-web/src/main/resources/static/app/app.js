define(function(require){
    var Vue = require("vue");
    var VueRouter = require("vue-router");
    var router = new VueRouter({
        routes: [
            { path: '/', component: Vue.component('home', function (resolve, reject) {
                    require(['/app/component/home.js'], resolve);
                })
            },
            {
                path: '/module/staff', component: Vue.component('staff', function (resolve, reject) {
                    require(['/app/component/staff.js'], resolve);
                })
            }
        ]
    });
    router.afterEach((to, from) => {
        appVM.onRouteChange(to);
    });

    var axios = require("axios");
    var appVM = new Vue({
        el: '#appDiv',
        router:router,
        data: {
            leftNavLock: false,
            isToggleNavMin: false,
            sideBodyStyle: {},
            sideMenuStyle: {
                height: '100px'
            },
            centerMainStyle: {
                height: '100px'
            },
            pageContentStyle: {},
            layoutConst: {
                topOffset: 40,
                footerOffset: 0,
                leftOffet: 0,
                leftNavLockWidthPX : '230px',
                leftNavMinWidthPX: '60px',
                maxSmWidth: 800
            },
            screenSize: {
                width: document.body.clientWidth,
                height: document.body.clientHeight
            },
            menus: [],
            sysRoute: [],
            navPathName: ''
        },
        mounted: function() {
            var thiz = this;
            window.onresize = function (ev) {
                if(thiz.resizeTO) clearTimeout(thiz.resizeTO);
                thiz.resizeTO = setTimeout(function() {
                    thiz.onWindowSizeChanged();
                }, 100);
            }

            this.onWindowSizeChanged();
            this.loadUserInfo();
        },
        methods: {
            loadUserInfo: function() {
                axios.get('/sys/route').then((resp) => {
                    var menuRoutes = [];
                    this.sysRoute = resp.data;
                    for(var i = 0; i < this.sysRoute.length; i++) {
                        var p = this.sysRoute[i];
                        menuRoutes.push({
                            path: p.path,
                            name: p.ctrl,
                            component: Vue.component(p.ctrl, function (resolve, reject) {
                                require([p.files], resolve);
                            })
                        })
                    }
                    console.log('menuRoutes', menuRoutes)
                    this.$router.addRoutes(menuRoutes);
                });

                axios.get('/sys/menus').then((resp) => {
                    this.menus = resp.data;
                    console.log('menus', this.menus);
                });
            },
            onWindowSizeChanged: function() {
                this.screenSize.width = document.body.clientWidth;
                this.screenSize.height = document.body.clientHeight;

                var min_height = ((window.innerHeight > 0) ? window.innerHeight : screen.height) - 1;
                min_height = min_height - this.layoutConst.topOffset- this.layoutConst.footerOffset - 10;
                if (min_height < 1) {
                    min_height = 1;
                }
                this.sideMenuStyle['height'] = (min_height - 84)+"px";
                this.centerMainStyle['height'] = (min_height + 9)+"px";

                console.log('height', this.sideMenuStyle['height'])
            },
            isLeftNavShow: function () {
                return true;
            },
            toggleNavMin: function () {
                this.isToggleNavMin = !this.isToggleNavMin;
            },
            onRouteChange: function (to) {
                //FIXME 计算菜单路径
                var name = '';
                for(var i = 0; i < this.sysRoute.length; i++) {
                    if(this.sysRoute[i].path == to.path) {
                        name = this.sysRoute[i].name;
                        break;
                    }
                }
                this.navPathName = name;
            },
            onLogout: function () {
                this.$bvModal.msgBoxConfirm('退出登录？', {
                    title: '确认', size: 'sm', buttonSize: 'sm', okVariant: 'danger',
                    okTitle: '确认', cancelTitle: '取消',
                    hideHeaderClose: false, centered: false
                }).then(value => {
                    if(value === true) {
                        window.location.href = '/sys/logout';
                    }
                });
            }
        }
    });
});
