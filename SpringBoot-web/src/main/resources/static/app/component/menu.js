define(function(require){
    var Vue = require("vue");
    Vue.component('sys-menu-item', {
        props: {
            text: String,
            url: String
        },
        template: '\
            <li v-on:click="onClick(url)">\
                <a class="md-button"><i class="fa fa-circle-o"></i>{{text}}</a>\
            </li>\
        ',
        methods: {
            onClick: function (url) {
                this.$router.push(url);
            }
        }
    });
    Vue.component('sys-menu-folder', {
        props: {
            text: String,
            url: String,
            subMenus: Array,
            isOpened: false
        },
        data: function() {
            return {
                opened: this.isOpened
            }
        },
        template: '\
        <li>\
            <a class="md-button md-button-toggle" v-on:click="toggle()">\
                <i class="fa fa-th"></i>\
                {{text}}\
                <i class="pull-right md-toggle-icon fa fa-angle-down" v-bind:class="{toggled : opened}"></i>\
            </a>\
            <transition name="menu-toggle-fade">\
            <ul class="menu-toggle-list" v-show="opened">\
                <li v-for="subMenu in subMenus">\
                    <sys-menu-item v-bind:text="subMenu.name" v-bind:url="subMenu.path" ></sys-menu-item>\
                </li>\
            </ul>\
            </transition>\
        </li>\
    ',
        methods: {
            toggle: function () {
                this.opened = !this.opened;
            }
        }
    });
});
