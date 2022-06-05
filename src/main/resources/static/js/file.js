function upload(){
// 创建formdata对象
    let formData = new FormData();
// 给formData对象添加<input>标签,注意与input标签的ID一致
    formData.append('file', $('#file')[0].files[0]);
    $.ajax({
        url: '/api/user/upload',//这里写你的url
        type: 'POST',
        data: formData,
        contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
        processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
        // dataType: 'json',//这里是返回类型，一般是json,text等
        // clearForm: true,//提交后是否清空表单数据
        success: function(data) {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
            alert('上传成功');
            location.reload();
        },
        error: function(data, status, e) {  //提交失败自动执行的处理函数。
            console.error(e);
        }
    });
}

function uploadInMeeting(mid){
// 创建formdata对象
    let formData = new FormData();
    let newMid = mid.toString();
// 给formData对象添加<input>标签,注意与input标签的ID一致
    formData.append('meetingfile', $('#meetingfile')[0].files[0]);
    $.ajax({
        url: '/api/user/uploadInMeeting?mid=' + newMid,//这里写你的url
        type: 'POST',
        data: formData,
        contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
        processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
        // dataType: 'json',//这里是返回类型，一般是json,text等
        // clearForm: true,//提交后是否清空表单数据
        success: function(data) {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
            alert('上传成功');
            location.reload();
        },
        error: function(data, status, e) {  //提交失败自动执行的处理函数。
            console.error(e);
        }
    });
}