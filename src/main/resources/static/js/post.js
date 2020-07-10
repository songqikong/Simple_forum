window.onload = function () {
    new Vue({
        el:"#post",
        data:{
            curPart:0,
        },
        methods:{
            inCurPart:function(part){
                this.curPart = part;
            },
            postPart:function () {
                axios({
                    method:'post',
                    url:"/postCheck",
                    data:{part:this.part },
                    headers:{'Content-Type': 'application/x-www-form-urlencoded'},
                    transformRequest: function(obj) {
                        var str = [];
                        for(var p in obj){
                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        }
                        return str.join("&");
                    }
                }).then((res)=>{
                    console.log(res.data);
                });
            }
        }
    })

}