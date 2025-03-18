$(document).ready(function () {
    document.getElementById('pangination').addEventListener('click', function (event) {


        if (event.target.tagName === 'BUTTON') {
            fetchDataFromButton(event.target);
        }

    })

    document.getElementById('search').addEventListener('input',function (event){
        var searchValue = this.value;  // 获取输入框的当前值
        console.log(222);
        $.ajax({
            url:'/user/search',
            method: 'Get',
            data: {value:searchValue},

            success:function (response){
                var userList=response;
                console.log(333);

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


                userList.forEach(function (user) {
                        let newRow = formContent.insertRow(); // 插入新行
                        let cell1 = newRow.insertCell(0); // 插入第一个单元格
                        cell1.textContent = user.username; // 填充用户名

                        let cell2 = newRow.insertCell(1); // 插入第二个单元格
                        cell2.textContent = user.password; // 填充密码
                    }
                )

            }

        })
    }



})

