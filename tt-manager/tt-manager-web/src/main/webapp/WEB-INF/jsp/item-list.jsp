<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--当前页面的HTML部分--%>
<div id="toolbar">
    <div style="padding: 5px; background-color: #fff;">
        <label>商品标题：</label>
        <input class="easyui-textbox" type="text" id="title" name="title">
        <label>商品状态：</label>
        <select id="status" name="status" class="easyui-combobox" >
            <option value="0">全部</option>
            <option value="1">正常</option>
            <option value="2">下架</option>
        </select>
        <!--http://www.cnblogs.com/wisdomoon/p/3330856.html-->
        <!--注意：要加上type="button",默认行为是submit-->
        <button onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
    </div>
    <div>
        <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
        <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true">下架</button>
        <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true">上架</button>
    </div>
</div>

<table id="itemListDg"></table>
<%--当前页面js部分--%>
<script>
    //点击搜索按钮的动作
    function searchForm() {
        $('#itemListDg').datagrid('load',{
            title: $.trim($('#title').val()),
            status:$('#status').combobox('getValue')
        });
    }
    function add() {
        myttshop.addTab('新增商品', 'item-add');
    }
    function edit() {
        console.log('编辑');
    }
    function remove() {
        var selections = $('#itemListDg').datagrid('getSelections');
        if (selections.length == 0) {
            //客户没有选中任何记录
            $.messager.alert('警告', '请选择记录再进行删除操作！');
            return;
        }
        //客户至少选中一条记录
        $.messager.confirm('确认', '您是否真的要删除记录？', function (r) {
            if (r) {
                //先定义存放id的数组
                var ids = [];
                //将selections中的每个ID存放数组中 push方法
                for (var i = 0; i < selections.length; i++) {
                    ids.push(selections[i].id)
                }
                //异步将ids发送到后台进行处理
                $.post(
                    //第一个参数url：异步请求提交给谁进行处理，字符串类型
                    'item/batch',
                    //第二个参数data：异步提交了什么给后台，Object类型
                    {"ids": ids},
                    //第三个参数success:异步处理成功后的回调函数，function
                    function (data) {
                        if (data > 0) {
                            $('#itemListDg').datagrid('reload');
                        }
                    }
                    //第四个参数dataType:数据返回类型，字符串类型
                    //,'json'
                );

            }
        });
    }
    function down(){
        console.log('下架');
    }
    function up(){
        console.log('上架');
    }

    $(function () {
        $('#itemListDg').datagrid({
            //这是表格属性
            method: 'get',
            multiSort: true,
            toolbar: '#toolbar',
            url: 'items',
            striped: true,
            pagination: true,
            pageSize: 20,
            pageList: [20, 50, 100],
            fit: true,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: '商品编号', sortable: true},
                {field: 'title', title: '商品标题', sortable: true},
                {
                    field: 'status', title: '商品状态', formatter: function (v, r, i) {
//                    console.group();
//                    console.log(v);
//                    console.log(r);
//                    console.log(i);
//                    console.groupEnd();
                    switch (v) {
                        case 1:
                            return '正常';
                            break;
                        case 2:
                            return '下架';
                            break;
                        case 3:
                            return '删除';
                            break;
                        default:
                            return '未知';
                            break;
                    }
                }
                },
                {field: 'sellPoint', title: '商品卖点'},
                {field: 'catName', title: '分类名称'},
                {
                    field: 'created', title: '创建时间', formatter: function (v, r, i) {
                    return moment(v).format('L');
                }
                },
                {field: 'priceView', title: '价格'}
            ]]
        });
    });
</script>