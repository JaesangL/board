$(document).ready(function() {
	
	 // 게시글 작성
	 $('#postForm').on('submit', function(event) {
                event.preventDefault(); // 폼의 기본 제출 동작을 막음
                insertBoard(); // AJAX 요청을 수동으로 트리거
      });
	
    // 게시글 수정 모드로 전환
    $('#editButton').click(function() {
        // Readonly 속성 해제
        $('#title').prop('readonly', false);
        $('#author').prop('readonly', false);
        $('#content').prop('readonly', false);

        // 버튼과 헤더 텍스트 변경
        $("#editButton").hide();
        $("#deleteButton").hide();
        $('#saveButton').show();
        $('h1').text('게시글 수정');
    });

    // 게시글 저장
    $('#saveButton').click(function() {
        const postData = {
            id: $('#boardId').val(),
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
            views: $('#views').val()
        };
        updateBoard(postData);
    });

    // 게시글 삭제
    $('#deleteButton').click(function() {
        const postId = $('#boardId').val();
        deleteBoard(postId);
    });
    
    // 뒤로가기
    $('#backButton').click(goBack);
    
    // 브라우저 뒤로가기 버튼 이벤트
    $(window).on('popstate', function(event) {
        // 사용자가 뒤로 가기 버튼을 눌렀을 때
        if (document.referrer.includes('/board/detail')) {
            // boardDetail 페이지에서 뒤로 가기를 누른 경우
            window.location.href = '/board/main'; // boardMain 페이지로 이동
        } else {
            location.reload(); // 다른 경우에는 그냥 페이지를 새로고침
        }
    });
});


//게시글 작성
function insertBoard() {
	const postData = {
        title: document.getElementById('title').value,
        author: document.getElementById('author').value,
        content: document.getElementById('content').value
    };
	
    $.ajax({
        url: '/board/insert',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(postData),
        success: function(post) {
            alert('게시글이 추가되었습니다.');
            window.location.href = '/board/main'; // 메인페이지로 리다이텍트
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error: ' + textStatus, errorThrown);
            alert('게시글 추가에 실패했습니다.');
        }
    });
}

//게시글 수정
function updateBoard(postData) {
    $.ajax({
        url: '/board/update',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(postData),
        success: function(post) {
            alert('게시글이 수정되었습니다.');
            window.location.href = '/board/main'; // 메인페이지로 리다이텍트
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error: ' + textStatus, errorThrown);
            alert('게시글 수정에 실패했습니다.');
        }
    });
}

//게시글 삭제
function deleteBoard(postId) {
    $.ajax({
        url: '/board/delete/' + postId,
        type: 'DELETE',
        success: function() {
            alert('게시글이 삭제되었습니다.');
            location.reload(); // 페이지를 새로고침하여 Thymeleaf로 렌더링된 데이터를 다시 로드
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error: ' + textStatus, errorThrown);
            alert('게시글 삭제에 실패했습니다.');
        }
    });
}

function goBack() {
    window.location.href = '/board/main';
}