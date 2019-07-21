define(function(require){
    var Vue = require("vue");
    var router = require('/app/router.js');
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
            menus: []
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
                    for(var i = 0; i < resp.data.length; i++) {
                        var p = resp.data[i];
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
            getNavName: function () {
                return "aa";
            }
        }
    });
})
