(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d12a59d"],{5803:function(t,a,e){},9406:function(t,a,e){"use strict";e.r(a);var n=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"dashboard-container"},[e("el-card",[e("div",{attrs:{slot:"header"},slot:"header"},[e("span",{staticStyle:{"font-weight":"bold"}},[t._v("输入问题：")])]),t._v(" "),e("div",{staticClass:"question"},[e("div",{staticClass:"question-container"},[e("el-input",{attrs:{placeholder:"瀑布沟适用什么鱼类保护措施",clearable:""},model:{value:t.question,callback:function(a){t.question=a},expression:"question"}}),t._v(" "),e("el-button",{staticClass:"qusetion-button",attrs:{type:"primary"},on:{click:t.search}},[t._v("提交")])],1),t._v(" "),e("div",{staticClass:"question-hot"},[t._v("\n        热门搜索：\n        "),e("span",[t._v("瀑布沟水电站的生态保护目标有哪些？")]),t._v(" "),e("span",[t._v("瀑布沟水电站的水质监测因子有哪些？")]),t._v(" "),e("span",[t._v("瀑布沟水电站的陆生植物物种组成有哪些？")])])])]),t._v(" "),e("el-row",{staticStyle:{"margin-top":"30px"}},[e("el-col",{staticStyle:{"padding-right":"20px"},attrs:{span:10}},[e("el-card",[e("div",{attrs:{slot:"header"},slot:"header"},[e("span",{staticStyle:{"font-weight":"bold"}},[t._v("答案：")])]),t._v(" "),t._l(t.answer,(function(a,n){return e("div",{key:n,staticClass:"answer"},[t._v(t._s(a))])}))],2)],1),t._v(" "),e("el-col",{staticStyle:{"padding-left":"20px"},attrs:{span:14}},[e("el-card",[e("div",{attrs:{slot:"header"},slot:"header"},[e("span",{staticStyle:{"font-weight":"bold"}},[t._v("图谱演示：")])]),t._v(" "),e("div",{staticStyle:{width:"100%",height:"500px"},attrs:{id:"graphChart"}})])],1)],1)],1)},s=[],i=(e("cc57"),e("6d57"),e("2389")),o=e.n(i),r=e("b775");function l(t){return Object(r["a"])({url:"/search",method:"get",params:t})}var c={name:"Dashboard",data:function(){return{question:"",answer:["增殖放流","人工繁殖技术研究","栖息地保护（产卵场、索饵场）"],chart:null}},methods:{search:function(){var t=this;l({name:this.question}).then((function(a){console.log(a);var e=[],n=[];a.data.forEach((function(t){e.push({name:t.name,x:t.x,y:t.y})})),a.links.forEach((function(t){n.push({source:t.source,target:t.target,label:{show:!0,formatter:t.name}})})),t.initChart({data:e,links:n})})).catch((function(t){console.log(t)}))},initChart:function(t){this.chart=o.a.init(document.getElementById("graphChart")),this.chart.setOption({animationDurationUpdate:1500,animationEasingUpdate:"quinticInOut",series:[{type:"graph",layout:"none",symbolSize:50,roam:!0,label:{show:!0,formatter:function(t){for(var a=4,e=[],n=0,s=t.name.length;n<s/a;n++){var i=t.name.slice(a*n,a*(n+1));e.push(i)}for(var o=0;o<e.length;o++)o!==e.length-1&&(e[o]=e[o]+"\n");return e.join("")}},edgeSymbol:["none","arrow"],edgeSymbolSize:[4,10],edgeLabel:{fontSize:12,color:"#000000"},data:t.data,links:t.links,lineStyle:{opacity:.9,width:2,curveness:0}}]})}}},d=c,u=(e("fa0d"),e("e90a")),h=Object(u["a"])(d,n,s,!1,null,"7a96e3f0",null);a["default"]=h.exports},fa0d:function(t,a,e){"use strict";var n=e("5803"),s=e.n(n);s.a}}]);