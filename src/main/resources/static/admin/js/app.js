function getSuccess(){
    new Noty({
        type: 'success',
        layout: 'topRight',
        text: 'Thực hiện thành công!',
        timeout: 3000,
        progressBar: true,
    }).show();
}

function getError(){
    new Noty({
        type: 'error',
        layout: 'topRight',
        text: 'Thực hiện thất bại!',
        timeout: 3000,
        progressBar: true,
    }).show();
}

function getWarning(){
    new Noty({
        type: 'warning',
        layout: 'topRight',
        text: 'Thực hiện không thành công!',
        timeout: 3000,
        progressBar: true,
    }).show();
}

function getInformation(){
    new Noty({
        type: 'information',
        layout: 'topRight',
        text: 'Thông báo!',
        timeout: 3000,
        progressBar: true,
    }).show();
}

function getAlert(){
    new Noty({
        type: 'alert',
        layout: 'topRight',
        text: 'Cảnh báo!',
        timeout: 3000,
        progressBar: true,
    }).show();
}

function getNotification(){
    new Noty({
        type: 'notification',
        layout: 'topRight',
        text: 'Thông báo!',
        timeout: 3000,
        progressBar: true,
    }).show();
}

function getSuccessWithMessage(message){
    new Noty({
        type: 'success',
        layout: 'topRight',
        text: message,
        timeout: 3000,
        progressBar: true,
    }).show();
}

