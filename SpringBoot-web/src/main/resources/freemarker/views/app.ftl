<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>代码生成器-web版</title>
    <link rel="shortcut icon" href="/resources/images/ac.ico"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link rel="stylesheet" href="/resources/js/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/js/bootstrap-vue/bootstrap-vue.min.css" />
    <link rel="stylesheet" href="/resources/css/fontawesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="/resources/css/app.css?v=${sysVersion}"/>

    <script type="text/javascript">
        var ctx = '';
        var sysVersion = '${sysVersion}';
        var projectName = 'SpringBootDemo';
    </script>

    <script src="/resources/js/require.js"></script>
</head>

<body>
<div id="appDiv">
<nav class="navbar navbar-dark sticky-top flex-md-nowrap pt-0 pb-0 pl-10 pr-10 navbar-ac">
    <a class="navbar-brand " href="#">Company name</a>
    <ul class="nav ">
        <form class="form-inline">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-warning my-2 my-sm-0" type="submit">Search</button>
        </form>
        <li class="nav-item">
            <a class="nav-link " href="#"><i class="fa fa-envelope-o">&nbsp;<span class="badge badge-danger">4</span></i></a>
        </li>
        <li class="nav-item">
            <a class="nav-link " href="#"><i class="fa fa-bell-o">&nbsp;<span class="badge badge-warning">4</span></i></a>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">设置</a>
            <div class="dropdown-menu dropdown-menu-right">
                <a class="dropdown-item" href="#">无我仙人</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">设置</a>
                <a class="dropdown-item" href="#">反馈</a>
                <a class="dropdown-item" href="#">其它</a>
            </div>
        </li>
        <li class="nav-item"><a class="nav-link" href="#">退出</a></li>
    </ul>
</nav>
<div class="container-fluid">
    <div class="row">
        <nav v-bind:class="{leftNavMin: isToggleNavMin,'leftNavExp':!isToggleNavMin}" class="left-nav d-none d-md-block bg-light sidebar p-0">
            <div class="user-panel">
                <div class="pull-left image">
                    <img alt="" class="img-circle" src="/resources/images/ac.ico">
                </div>
                <div class="pull-left info">
                    <p>无我仙人</p>
                    <p>autocoding@163.com</p>
                </div>
            </div>
            <div class="sidebar-sticky" style="overflow: scroll;" v-bind:style="sideMenuStyle" >
                <ul class="docs-menu">
                    <template v-for="menu in menus">
                        <sys-menu-item v-if="menu.type === 'link'" v-bind:text="menu.name" v-bind:url="menu.path" ></sys-menu-item>
                        <sys-menu-folder v-else v-bind:text="menu.name" v-bind:url="menu.path" v-bind:sub-menus="menu.subMenus" ></sys-menu-folder>
                    </template>
                </ul>
            </div>
            <div class="sidebar-footer" v-on:click="toggleNavMin();">
                <div class="pull-right">
                    <i class="toggleNavMin fa fa-angle-left" aria-hidden="true" v-bind:class="{toggled: isToggleNavMin}"></i>
                </div>
            </div>
        </nav>
        <div role="main" class="col center-main container" v-bind:style="centerMainStyle">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><router-link :to="'/'"><i class="fa fa-home"></i>首页</router-link></li>
                    <li class="breadcrumb-item active" aria-current="page">{{navPathName}}</li>
                </ol>
            </nav>
            <div>
                <router-view></router-view>

            </div>
        </div>
    </div>
</div>
</div>
<script src="/app/main.js"></script>
</body>
</html>
