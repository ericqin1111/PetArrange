$(document).ready(function () {

    //绑定Page按钮事件,跳转到fetchDataFromButton方法
    document.getElementById('pangination').addEventListener('click', function (event) {
        if (event.target.tagName === 'BUTTON') {
            fetchDataFromButton(event.target);
        }
    })



    //获取输入框变化，改变table
    document.getElementById('search').addEventListener('input',function (event){
        var searchValue = this.value;  // 获取输入框的当前值
        if(searchValue===''){
            const event = new Event('click');
// 选择一个元素
            const button = document.getElementById('pageBtn1');
// 模拟点击事件
            button.dispatchEvent(event);
        }
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
//测试




                //
                // const sessionElement = document.getElementById("sessionData");
                // const totalPages = parseInt(sessionElement.getAttribute("data-total-pages"));
                // const currentPage = parseInt(sessionElement.getAttribute("data-current-page"));
                //
                // const paginationContainer = document.getElementById("pagination");
                // paginationContainer.innerHTML = ""; // 清空现有内容
                //
                // if (totalPages <= 5) {
                //     for (let i = 1; i <= totalPages; i++) {
                //         const button = document.createElement("button");
                //         button.innerText = i;
                //         button.id = "pageBtn" + i;
                //         button.setAttribute("data-page", i);
                //         paginationContainer.appendChild(button);
                //     }
                // } else if (totalPages > 5 && currentPage <= 5) {
                //     for (let i = 1; i <= 5; i++) {
                //         const button = document.createElement("button");
                //         button.innerText = i;
                //         button.id = "pageBtn" + i;
                //         button.setAttribute("data-page", i);
                //         paginationContainer.appendChild(button);
                //     }
                //     const span = document.createElement("span");
                //     span.innerText = "……";
                //     paginationContainer.appendChild(span);
                //
                //     const lastPageButton = document.createElement("button");
                //     lastPageButton.innerText = totalPages;
                //     lastPageButton.id = "pageBtn" + totalPages;
                //     lastPageButton.setAttribute("data-page", totalPages);
                //     paginationContainer.appendChild(lastPageButton);
                // } else if (totalPages > 5 && currentPage > 5 && currentPage + 2 <= totalPages) {
                //     const firstPageButton = document.createElement("button");
                //     firstPageButton.innerText = "1";
                //     firstPageButton.setAttribute("data-page", 1);
                //     firstPageButton.id = "pageBtn1";
                //     paginationContainer.appendChild(firstPageButton);
                //
                //     const span = document.createElement("span");
                //     span.innerText = "……";
                //     paginationContainer.appendChild(span);
                //
                //     for (let i = currentPage - 2; i <= currentPage + 2; i++) {
                //         const button = document.createElement("button");
                //         button.innerText = i;
                //         button.id = "pageBtn" + i;
                //         button.setAttribute("data-page", i);
                //         paginationContainer.appendChild(button);
                //     }
                //
                //     const lastPageButton = document.createElement("button");
                //     lastPageButton.innerText = totalPages;
                //     lastPageButton.setAttribute("data-page", totalPages);
                //     lastPageButton.id = "pageBtn" + totalPages;
                //     paginationContainer.appendChild(lastPageButton);
                // } else if (currentPage > 5 && currentPage + 2 > totalPages && totalPages > 5) {
                //     const firstPageButton = document.createElement("button");
                //     firstPageButton.innerText = "1";
                //     firstPageButton.setAttribute("data-page", 1);
                //     firstPageButton.id = "pageBtn1";
                //     paginationContainer.appendChild(firstPageButton);
                //
                //     const span = document.createElement("span");
                //     span.innerText = "……";
                //     paginationContainer.appendChild(span);
                //
                //     for (let i = currentPage - 2; i <= totalPages; i++) {
                //         const button = document.createElement("button");
                //         button.innerText = i;
                //         button.id = "pageBtn" + i;
                //         button.setAttribute("data-page", i);
                //         paginationContainer.appendChild(button);
                //     }
                // }
                //






//测试
                var count=1;
                userList.forEach(function (user) {
                    if(count<6) {


                        var checkbox = document.createElement('input');
                        checkbox.type = 'checkbox';
                        checkbox.className = 'select-row';

                        checkbox.id = 'checkbox-' + count;

                        var modBtn = document.createElement('button');
                        modBtn.id = 'ModBtn-' + count;
                        modBtn.classList = "modify_btn";
                        modBtn.textContent = "修改";


                        // var delBtn = document.createElement('button');
                        // delBtn.id='DelBtn-'+count;
                        // delBtn.textContent="删除";

                        var saveBtn = document.createElement('button');
                        saveBtn.id = 'SaveBtn-' + count;
                        saveBtn.classList = "save_btn";
                        saveBtn.textContent = "保存";

                        var newRow = formContent.insertRow(); // 在第一行插入新行


                        var blockCellCKB = newRow.insertCell(0);
                        var cellName = newRow.insertCell(1);
                        var cellPassword = newRow.insertCell(2);
                        var blockCellModify = newRow.insertCell(3);
                        // var blockCellDel=newRow.insertCell(4);
                        var emptyCell = newRow.insertCell(4);
                        var cellSave = newRow.insertCell(5);


                        cellName.textContent = user.username;
                        cellPassword.textContent = user.password;
                        blockCellCKB.appendChild(checkbox);
                        blockCellModify.appendChild(modBtn);
                        // blockCellDel.appendChild(delBtn);
                        cellSave.appendChild(saveBtn);

                        count++;
                        // let newRow = formContent.insertRow(); // 插入新行
                        //
                        // let cell1 = newRow.insertCell(0); // 插入第一个单元格
                        // cell1.textContent = user.username; // 填充用户名
                        //
                        // let cell2 = newRow.insertCell(1); // 插入第二个单元格
                        // cell2.textContent = user.password; // 填充密码
                    }
                    else return;
                })
            }
        })

    })


    //获取页面
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
                        count+=1;

                        var checkbox = document.createElement('input');
                        checkbox.type = 'checkbox';
                        checkbox.className = 'select-row';

                        checkbox.id = 'checkbox-' + count;

                        var modBtn=document.createElement('button');
                        modBtn.id='ModBtn-'+ count;
                        modBtn.classList="modify_btn";
                        modBtn.textContent="修改";


                        // var delBtn = document.createElement('button');
                        // delBtn.id='DelBtn-'+count;
                        // delBtn.textContent="删除";

                        var saveBtn=document.createElement('button');
                        saveBtn.id='SaveBtn-'+count;
                        saveBtn.classList="save_btn";
                        saveBtn.textContent="保存";

                        var newRow = formContent.insertRow(); // 在第一行插入新行



                        var blockCellCKB=newRow.insertCell(0);
                        var cellName = newRow.insertCell(1);
                        var cellPassword = newRow.insertCell(2);
                        var blockCellModify=newRow.insertCell(3);
                        // var blockCellDel=newRow.insertCell(4);
                        var emptyCell=newRow.insertCell(4);
                        var cellSave = newRow.insertCell(5);


                        cellName.textContent=user.username;
                        cellPassword.textContent=user.password;
                        blockCellCKB.appendChild(checkbox);
                        blockCellModify.appendChild(modBtn);
                        // blockCellDel.appendChild(delBtn);
                        cellSave.appendChild(saveBtn);
                    }
                )

            }

        })
    }


    document.getElementById("user2_form").addEventListener('click',function (event){


        if (event.target.classList.contains("save_btn")||event.target.classList.contains("temSave_btn")){
                saveData(event.target);
        }
        else if(event.target.classList.contains("modify_btn")){
            modifyData(event.target);
        }
        else if(event.target.classList.contains("del_btn")||event.target.classList.contains("temDel_btn")){
            deleteData(event.target);
        }
    })


    document.getElementById("addBtn").addEventListener('click',function (){
        var table=document.getElementById("user2_form");
        var newRow = table.insertRow(1); // 在第一行插入新行

        var blockCellCKB=newRow.insertCell(0);
        var cellName = newRow.insertCell(1);
        var cellPassword = newRow.insertCell(2);
        var cellDel=newRow.insertCell(3);
        // var emptyCell=newRow.insertCell(4);
        var cellSave = newRow.insertCell(4);

        var temDelButton=document.createElement('button');
        temDelButton.textContent="放弃";
        temDelButton.classList="temDel_btn";
        temDelButton.id="temDel_btn";

        cellDel.appendChild(temDelButton);

        // 创建输入框
        cellName.innerHTML = '<input type="text" id="temName" placeholder="输入用户名">';
        cellPassword.innerHTML = '<input type="temDel_btn" id="temDel" placeholder="输入密码">';
        cellSave.innerHTML = '<button class="temSave_btn" id="temSave">保存</button>';
    })

    function saveData(button) {
        var row = button.parentElement.parentElement; // 获取当前行
        var usernameInput;
        var passwordInput;

        if(button.id=="temSave"){
            usernameInput = row.cells[1].querySelector("input");
            username=usernameInput.value;
            passwordInput = row.cells[2].querySelector("input");
        }
        else if(button.classList.contains("save_btn")){
            usernameInput = row.cells[1].textContent;
            username = usernameInput;
            console.log(usernameInput);
            passwordInput = row.cells[2].querySelector("input");
            console.log(passwordInput);
        }




        console.log("username:"+username);
        var password = passwordInput.value;
        console.log("password:"+password);

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

    function modifyData(button) {


        var row = button.parentElement.parentElement; // 获取 <tr> 行
        var rowIndex = row.rowIndex;
        //
        // row.cells[2].innerHTML="";
        // var cellPassword = row.insertCell(2);
        row.cells[2].innerHTML = '<input type="text" id="temPass" placeholder="输入新密码">';

    }

    function deleteData(button){
        var table=document.getElementById("user2_form");
        var row = button.parentElement.parentElement; // 获取 <tr> 行
        var rowIndex=row.rowIndex;

        if(button.classList.contains("del_btn")){
            console.log(111);
        }
        else if(button.classList.contains("temDel_btn")){
            table.deleteRow(rowIndex);
        }
    }


    document.getElementById("delBtn").addEventListener('click',function (){
        var checkedCheckboxes = document.querySelectorAll('.select-row:checked');
        var userList=[];
        var rowList=[];
        if(checkedCheckboxes.length===0){
            alert("未选中任何用户!");
        }
        else {
            checkedCheckboxes.forEach(function(checkbox) {
                var table=document.getElementById("user2_form");
                var row = checkbox.closest('tr');
                var rowIndex=row.rowIndex;
                var username=row.cells[1].textContent;
                userList.push(username);
                rowList.push(rowIndex);
            })

            var req=JSON.stringify(userList);
            console.log(req);
        }

        $.ajax({
            url:'/user/delUser',
            method:'POST',
            contentType: 'application/json',
            data:JSON.stringify({ userList: userList }),
            success:function (response){
                if (response!=0){
                    console.log("删除成功");
                }
                // rowList.forEach(function (row1){
                //     table.deleteRow(row1);
                // })
            },
            error:function (){
                alert("删除失败");
            }
            // checkedCheckboxes.forEach(function(checkbox) {
            //     var table=document.getElementById("user2_form");
            //     var row = button.parentElement.parentElement; // 获取 <tr> 行
            //     var rowIndex=row.rowIndex;
        });
        // 输出选中的复选框的值


    })


})

