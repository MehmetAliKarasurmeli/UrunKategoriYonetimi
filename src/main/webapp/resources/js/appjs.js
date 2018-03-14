
function urunSil(id) {
	
	var yanit = confirm("URUNU SILMEK ISTEDIGINIZE EMIN MISINIZ?");
	if (yanit == true) {
		$.ajax({
			url : "/urunkategori/urunSil",
			type : "POST",
			data : {
				'uid' : id
			},
			success : function(gelen) {
				if (gelen == "") {
					alert("silme islemi basarisiz!");
				} else {
					$("#trUrun" + gelen).fadeOut();
				}
			}
		});
	}
}

$(document).ready(function() {
	$('#btnKatEkle').click(function() {

		var kadi = $('#kadi').val();

		$.ajax({
			url : '/urunkategori/katEkle',
			type : 'POST',
			data : {
				'kadi' : kadi,
			},
			success : function(data) {

				if (data != "") {
					$('#ukid').html(data);
					$('#kadi').val("");
					$('#kadi').focus();
				}
			}
		})
	});

	$('#btnUrunEkle').click(function() {

		var uadi = $('#uadi').val();
		var ukid = $('#ukid').val();

		$.ajax({
			url : '/urunkategori/urunEkle',
			type : 'POST',
			data : {
				'uadi' : uadi,
				'ukid' : ukid,
			},
			success : function(data) {

				if (data != "") {
					$('#tablo').append(data);
					$('#uadi').val("");
					$('#uadi').focus();
				}
			}
		})
	});
});