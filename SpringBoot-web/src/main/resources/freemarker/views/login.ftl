<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>SpringBootDemo</title>
    <link rel="shortcut icon" href="/resources/images/ac.ico"/>
    <meta charset="utf-8" />
    <meta name="description" content="SpringBootDemo" />
    <meta name="title" content="SpringBootDemo" />
    <meta name="keywords" content="SpringBootDemo" />
    <meta name="description" content="SpringBootDemo" />

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />

    <link rel="stylesheet" href="/resources/js/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/js/bootstrap-vue/bootstrap-vue.min.css" />
    <link rel="stylesheet" href="/resources/css/fontawesome/css/font-awesome.min.css" />

    <style type="text/css">
        body {
            background-image: url("/resources/login_bootstrap/img/backgrounds/sc2.jpg");
            background-size: cover;
            font-family: 'Roboto', sans-serif;
            font-size: 16px;
            color: #888;
            line-height: 30px;
            background-color: #f8f8f8;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            font-weight: 400;
            overflow-x: hidden;
            overflow-y: auto;
        }
        strong { font-weight: 400; }

        .main-container {
            padding-top: 66px;
        }
        .description,h1 {
            text-align: center;
            color: white;
        }

        .main-container .form-top {
            overflow: hidden;
            padding: 0 25px 0px 25px;
            background: #fff;
            -moz-border-radius: 4px 4px 0 0; -webkit-border-radius: 4px 4px 0 0; border-radius: 4px 4px 0 0;
            text-align: left;
        }
        .main-container .form-top .form-top-left {
            float: left;
            width: 75%;
            padding-top: 25px;
        }

        .main-container .form-top .form-top-right {
            float: left;
            width: 25%;
            padding-top: 5px;
            font-size: 66px;
            color: #ddd;
            line-height: 100px;
            text-align: right;
        }

        .main-container .form-bottom {
            padding: 25px 25px 30px 25px;
            background: #eee;
            -moz-border-radius: 0 0 4px 4px; -webkit-border-radius: 0 0 4px 4px; border-radius: 0 0 4px 4px;
            text-align: left;
        }
    </style>

    <script src="/resources/js/jquery/3.2.1/jquery.slim.min.js"></script>
    <script src="/resources/js/popper/popper.min.js"></script>
    <script src="/resources/js/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/js/vue/vue.js"></script>
    <script src="/resources/js/axios/axios.min.js"></script>
    <script src="/resources/js/bootstrap-vue/bootstrap-vue.min.js"></script>

    <script type="text/javascript">
        Vue.use(bootstrapVue);
    </script>
</head>
<body>

<div id="loginDiv" class="container justify-content-md-center main-container">
    <div class="row ">
        <div class="offset-md-3 col-md-6">
            <h1><strong>SpringBootDemo</strong></h1>
        </div>
    </div>
    <div class="row description">
        <div class="offset-md-3 col-md-6">
            <p>登录SpringBootDemo!</p>
        </div>
    </div>
    <div class="row">
        <div class="form-top offset-md-3 col-md-6">
            <div class="form-top-left">
                <h3 style="font-weight: 400;">登录</h3>
                <p>输入用户名和密码:</p>
            </div>
            <div class="form-top-right">
                <i class="fa fa-lock"></i>
            </div>
        </div>
        <div class="form-bottom offset-md-3 col-md-6">
            <form>
                <div class="form-group">
                    <input v-model="name" type="text" class="form-control" placeholder="用户名...">
                </div>
                <div class="form-group">
                    <input v-model="password" type="password" class="form-control" placeholder="密码">
                </div>
                <button type="button" v-on:click="login" class="btn btn-danger btn-block">登录</button>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    var loginVM = new Vue({
        el: '#loginDiv',
        data: {
            name:'',password:''
        },
        methods: {
            login: function () {
                axios.post('/sys/login', {username: this.name, password: this.password}).then((response) => {
                    if(response.data.result == 'success') {
                        window.location.href = '/';
                    }
                    else {
                        this.makeToast(response.data.info);
                    }
                });
            },
            makeToast: function (info) {
                this.$bvToast.toast(info, {title: '错误',variant: 'danger',solid: true})
            }
        }
    });
</script>
</body>
</html>