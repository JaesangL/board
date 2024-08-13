/** 
$(document).ready(function() {
    loadBoardList();
});


// board.js
function loadBoardList() {
    $.ajax({
        url: 'http://localhost:8080/board/main',
        type: 'GET',
        dataType: "json",
        success: function(data) {
            let tableBody = $('.board-list tbody');
            tableBody.empty();
            data.forEach(function(post) {
                let row = `
                    <tr>
                        <td>${post.id}</td>
                        <td>${post.title}</td>
                        <td>${post.author}</td>
                        <td>${post.date}</td>
                        <td>${post.views}</td>
                    </tr>`;
                tableBody.append(row);
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error: ' + textStatus, errorThrown);
            alert('게시글 목록을 불러오는 데 실패했습니다.');
        }
    });
}
*/
//게시글 작성
function insertBoard(postData) {
    $.ajax({
        url: '/board/insert',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(postData),
        success: function(post) {
            alert('게시글이 추가되었습니다.');
            location.reload(); // 페이지를 새로고침하여 Thymeleaf로 렌더링된 데이터를 다시 로드
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
            location.reload(); // 페이지를 새로고침하여 Thymeleaf로 렌더링된 데이터를 다시 로드
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