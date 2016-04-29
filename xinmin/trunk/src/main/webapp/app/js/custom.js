$(function() {
	var imgs = $('img');
	imgs.click(function(e) {
		var index = imgs.indexOf(e.target);
		switch(index) {
			case 1:
				goCreate();
				break;
			case 2:
				goJoin();
				break;
			default:
		}
	});
});

function goCreate() {
	location.href = './crate.html';
}