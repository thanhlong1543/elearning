Dropzone.autoDiscover = false;
$(document).ready(function(){
	var uploadUrl = $("#dZUpload").data("upload");
	var loadUrl = $("#dZUpload").data("load");
	$("#dZUpload").dropzone({
		url: uploadUrl,
		addRemoveLinks : true,
		paramName : "file",
		maxFilesize : 2,
		acceptedFiles : 'image/*,.jpg,.png,.jpeg',
		success: function(file, response){
			var imgName = response.url;
			file.previewElement.classList.add("dz-success");
			$('#avatar').val(imgName);
			console.log("Successfully upload : " + imgName);
		},
		error: function (file, response){
			console.log(response)
			file.previewElement.classList.add("dz-success");
		},
		init: function (){
			$.ajax({
				url: loadUrl,
				type: 'GET',
				dataType: 'JSON',
				data: {
					fileName: $('#avatar').val
				},
				success: function (res) {
					if(res){
						var mockFile = {name: res.name, size: 12345};
						thisDropzone.options.addedfile.call(thisDropzone, mockFile);
						thisDropzone.options.thumbail.call(thisDropzone, mockFile, res.url);
						mockFile.previewElement.classList.add('dz-success');
						mockFile.previewElement.classList.add('dz-complete');
					}		
				},
				error: function(err){
					console.log(err)
				}
			})
		}
	});
});