$(document).ready(function() {
    initEventHandlers();
});

function initEventHandlers() {
    // 게시글 작성
    $('#postForm').on('submit', handleFormSubmit);
    
    // 게시글 수정 모드로 전환
    $('#editButton').click(enableEditMode);
    
    // 게시글 수정
    $('#saveButton').click(handleSave);
    
    // 게시글 삭제
    $('#deleteButton').click(handleDelete);
    
    // 뒤로가기
    $('#backButton').click(goBack);
    
    // 브라우저 뒤로가기 버튼 이벤트
    $(window).on('popstate', handlePopState);
}

// 게시글 작성 핸들러
function handleFormSubmit(event) {
    event.preventDefault(); // 폼의 기본 제출 동작을 막음
    insertBoard();
}

// 게시글 작성
function insertBoard() {
    if (validateForm()) { 
        const postData = getPostData(); 
    
        $.ajax({
            url: '/board/insert', 
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(postData),
            success: function(post) {
                alert('게시글이 추가되었습니다.'); 
                redirectToMain(); // 메인 페이지로 이동
            },
            error: function(jqXHR, textStatus, errorThrown) {
                handleAjaxError('게시글 추가에 실패했습니다.', textStatus, errorThrown); 
            }
        });
    }
}

// 게시글 수정 모드로 전환
function enableEditMode() {
    setReadOnly(false);
    toggleButtons();
    setHeaderText('게시글 수정');
}

// 게시글 수정 핸들러
function handleSave() {
    if (validateForm()) { 
        const postData = getPostDataWithId(); 
        updateBoard(postData); 
    }
}

// 게시글 수정
function updateBoard(postData) {
    $.ajax({
        url: '/board/update',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(postData),
        success: function(post) {
            alert('게시글이 수정되었습니다.');
            redirectToMain();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            handleAjaxError('게시글 수정에 실패했습니다.', textStatus, errorThrown);
        }
    });
}

// 게시글 삭제 핸들러
function handleDelete() {
    const postId = $('#boardId').val(); // boardId 값을 가져옴
    if (confirm('정말 게시글을 삭제하시겠습니까?')) {
        deleteBoard(postId);
    }
}

// 게시글 삭제
function deleteBoard(postId) {
    $.ajax({
        url: '/board/delete/' + postId,
        type: 'DELETE',
        success: function() {
            alert('게시글이 삭제되었습니다.');
            redirectToMain();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            handleAjaxError('게시글 삭제에 실패했습니다.', textStatus, errorThrown);
        }
    });
}

// 뒤로가기 핸들러
function handlePopState(event) {
    if (document.referrer.includes('/board/detail')) {
        redirectToMain();
    } else {
        location.reload();
    }
}

// 유효성 검사
function validateForm() {
    const title = $('#title').val(); // 제목 가져오기
    const author = $('#author').val(); // 작성자 가져오기
    const content = $('#content').val(); // 내용 가져오기

    if (!title || title.length > 20) { // 제목이 없거나 길이가 100자를 초과하면
        alert('제목을 확인해주세요. (최대 20자)'); // 경고 메시지 표시
        return false;
    }
    if (!author || author.length > 20) { // 작성자가 없거나 길이가 50자를 초과하면
        alert('작성자를 확인해주세요. (최대 20자)'); // 경고 메시지 표시
        return false;
    }
    if (!content || content.length > 200) { // 내용이 없거나 길이가 1000자를 초과하면
        alert('내용을 확인해주세요. (최대 200자)'); // 경고 메시지 표시
        return false;
    }
    return true; // 모든 검사를 통과한 경우 true 반환
}

// 데이터 가져오기
function getPostData() {
    return {
        title: $('#title').val(),
        author: $('#author').val(),
        content: $('#content').val()
    };
}

// 데이터 가져오기 (ID 포함)
function getPostDataWithId() {
    return {
        id: $('#boardId').val(),
        title: $('#title').val(),
        author: $('#author').val(),
        content: $('#content').val(),
        views: $('#views').val()
    };
}

function setReadOnly(readOnly) {
    $('#title, #author, #content').prop('readonly', readOnly);
}

function toggleButtons() {
    $("#editButton, #deleteButton").toggle();
    $('#saveButton').toggle();
}

function setHeaderText(text) {
    $('h1').text(text);
}

function redirectToMain() {
    window.location.href = '/board/main';
}

function handleAjaxError(message, textStatus, errorThrown) {
    console.error('Error: ' + textStatus, errorThrown);
    alert(message);
}

function goBack() {
    redirectToMain();
}