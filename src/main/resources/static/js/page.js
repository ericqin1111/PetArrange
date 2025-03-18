$(document).ready(function () {
    document.getElementById('pangination').addEventListener('click', function (event) {


        if (event.target.tagName === 'BUTTON') {
            fetchDataFromButton(event.target);
        }


    })




    document.getElementById('search').addEventListener('input',function (event){
        var searchValue = this.value;  // 获取输入框的当前值
        $.ajax({
            url:'/user/search',
            method: 'Get',
            data: {value:searchValue},

            success:function (response){

                var userList=response;

                var formContent = document.getElementById("user2_form");
                // formContent.innerHTML="";
                // formContent.offsetHeight;
                while (formContent.rows.length > 1) {
                    formContent.deleteRow(1);
                }
                userList.forEach(function (user) {

                    let newRow = formContent.insertRow(); // 插入新行
                    let cell1 = newRow.insertCell(0); // 插入第一个单元格
                    cell1.textContent = user.username; // 填充用户名

                    let cell2 = newRow.insertCell(1); // 插入第二个单元格
                    cell2.textContent = user.password; // 填充密码
                })
            }
        })

    })

    function fetchDataFromButton(button) {

        let page = button.getAttribute('data-page');

        $.ajax({
            url: '/user/updateUser',
            method: 'GET',
            data: {page: page},

            success: function (response) {

                console.log(document.getElementById("user2_form").tagName);


                let map = response;
                let userList = response.userList;
                var formContent = document.getElementById("user2_form");
                // formContent.innerHTML="";
                // formContent.offsetHeight;
                while (formContent.rows.length > 1) {
                    formContent.deleteRow(1);
                }


                let count=0;
                userList.forEach(function (user) {
                        // let newRow = formContent.insertRow(); // 插入新行
                        // let cell1 = newRow.insertCell(0); // 插入第一个单元格
                        // cell1.textContent = user.username; // 填充用户名
                        //
                        // let cell2 = newRow.insertCell(1); // 插入第二个单元格
                        // cell2.textContent = user.password; // 填充密码
                        // count+=1;

                        var checkbox = document.createElement('input');
                        checkbox.type = 'checkbox';
                        checkbox.className = 'select-row';

                        checkbox.id = 'checkbox-' + count;

                        var modBtn=document.createElement('button');
                        modBtn.id='ModBtn-'+ count;
                        modBtn.textContent="修改";


                        var delBtn = document.createElement('button');
                        delBtn.id='DelBtn-'+count;
                        delBtn.textContent="删除";

                        var saveBtn=document.createElement('button');
                        saveBtn.id='SaveBtn-'+count;
                        saveBtn.textContent="保存";

                        var newRow = formContent.insertRow(); // 在第一行插入新行


                        var blockCellCKB=newRow.insertCell(0);
                        var cellName = newRow.insertCell(1);
                        var cellPassword = newRow.insertCell(2);
                        var blockCellModify=newRow.insertCell(3);
                        var blockCellDel=newRow.insertCell(4);
                        var cellSave = newRow.insertCell(5);

                        cellName.textContent=user.username;
                        cellPassword.textContent=user.password;
                        blockCellCKB.appendChild(checkbox);
                        blockCellModify.appendChild(modBtn);
                        blockCellDel.appendChild(delBtn)
                        cellSave.appendChild(saveBtn);
                    }
                )

            }

        })
    }


    document.getElementById("user2_form").addEventListener('click',function (event){
        if (event.target.classList.contains("save_btn")) {
            saveData(event.target);
        }
    })


    document.getElementById("addBtn").addEventListener('click',function (){
        var table=document.getElementById("user2_form");
        var newRow = table.insertRow(1); // 在第一行插入新行

        var blockCellCKB=newRow.insertCell(0);
        var cellName = newRow.insertCell(1);
        var cellPassword = newRow.insertCell(2);
        var blockCellModify=newRow.insertCell(3);
        var blockCellDel=newRow.insertCell(4);
        var cellSave = newRow.insertCell(5);



        // 创建输入框
        cellName.innerHTML = '<input type="text" id="temName" placeholder="输入用户名">';
        cellPassword.innerHTML = '<input type="text" id="temPass" placeholder="输入密码">';
        cellSave.innerHTML = '<button class="save_btn" id="temSave">保存</button>';
    })

    function saveData(button) {
        var row = button.parentElement.parentElement; // 获取当前行
        // var usernameInput = row.cells[1].querySelector("input");
        // var passwordInput = row.cells[2].querySelector("input");

        var usernameInput = row.cells[1].textContent;
        var passwordInput = row.cells[2].textContent;

        var username = usernameInput.value;

        var password = passwordInput.value;

        if (!username || !password) {
            alert("用户名和密码不能为空！");
            return;
        }

        $.ajax({
            url: '/user/addUser',
            method: "POST",

            data: ({username: username, password: password}),
            success: function (response) {
                if (response != 0) {
                    alert("数据已存入数据库！");
                }

                row.cells[1].innerHTML = username;  // 显示用户名
                row.cells[2].innerHTML = password;  // 显示密码
            },
            error: function () {
                alert("添加失败，请检查服务器！");
            }
        });
    }



})

