const DELETE_MESSAGE = "削除してもよろしいですか？"
$('.delete-action').click(function() {
  if(!confirm(DELETE_MESSAGE)){
    return false;
  }
});

const LOGOUT_MESSAGE = "ログアウトしてもよろしいですか？"
$('.logout-action').click(function() {
  if(!confirm(LOGOUT_MESSAGE)){
    return false;
  }
});