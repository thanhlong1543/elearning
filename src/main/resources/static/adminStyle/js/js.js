$(document).ready(function(){
    $('#editRole').click(function(e) {
        $('input[type="text"]').each(function() {
            if( $.trim($(this).val()) == '') {
                $(this).parents('p').addClass('warning');
                return false;
            }
        });
    });
});

$(document).ready(function(){
    $('#btn-search').click(function(){
        SearchCourse();
        return 1;
    });
});

 function SearchCourse(){
	var state = "", searchCourse = "";
    var titleCourse = $("#titleCourse").val();
    var re =  titleCourse.replace(" ", "%20");
    var showCourse = "";
    console.log(re);
	axios({
	    method: 'GET',
	    url: 'http://localhost:8085/apiClient/course/search?searchCourse='+re.toString(),
	    data: null
		}).then(function (response) {
            for (i in response.data) {
                showCourse += 
                "<tr>" +
                    "<th class='text-center'>" + (++i) + "</th>"+
                    "<td class='text-center'>" + response.data[i].title + "</td>"+
                    "<td class='text-center'>" +  "<img src='/adminStyle/images/course/"+ response.data[i].image + "' class='border card-img-top rounded mx-auto d-block'>"  + "</td>"+
                    "<td class='text-center'>" + response.data[i].leturesCount + "</td>"+
                    "<td class='text-center'>" + response.data[i].price +".000 đ" +"</td>"+
                    "<td class='text-center'>" + " <a href='/admin/course/edit?id="+response.data[i].id  +"' class='btn btn-sm btn-info btn-round py-1 font-weight-bold'>Sửa</a>"+
                    "<a href='/admin/course/delete?id="+response.data[i].id  +"' class='btn btn-sm btn-danger btn-round py-1 font-weight-bold'>Xóa</a>" +
                    "</td>"+
                "</tr>";
                document.getElementById("showCourse").innerHTML =  showCourse;
            }
        })
        .catch(function (error) {
            console.log("error connect");
        });
}
