$(function() {
	$('.field, textarea').focus(function() {
        if(this.title==this.value) {
            this.value = '';
        }
    }).blur(function(){
        if(this.value=='') {
            this.value = this.title;
        }
    });

    $('#slider ul').jcarousel({
    	scroll: 1,
		auto: 7,
		itemFirstInCallback : mycarousel_firstCallback,
        wrap: 'both'
    });
   function mycarousel_firstCallback(carousel, item, idx) {
        $('#slider .nav a').bind('click', function() {
            carousel.scroll(jQuery.jcarousel.intval($(this).text()));
            $('#slider .nav a').removeClass('active');
            $(this).addClass('active');
            return false;
        });
        $('#slider .nav a').removeClass('active');
        $('#slider .nav a').eq(idx-1).addClass('active');
    }
	
    $('#best-sellers ul').jcarousel({
        auto: 5,
        scroll: 1,
        wrap: 'circular'
    });
	
     if ($.browser.msie && $.browser.version.substr(0,1)<7) {
        DD_belatedPNG.fix('#logo h1 a, .read-more-btn, #slider .image img, #best-sellers .jcarousel-prev, #best-sellers .jcarousel-next, #slider .jcarousel-container, #best-sellers .price, .shell, #footer, .products ul li a:hover');
    }
});

var $TABLE = $('#table');
var $BTN = $('#export-btn');
var $EXPORT = $('#export');

$('.table-add').click(function () {
  var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
  $TABLE.find('table').append($clone);
});

$('.table-remove').click(function () {
  $(this).parents('tr').detach();
});

$('.table-up').click(function () {
  var $row = $(this).parents('tr');
  if ($row.index() === 1) return; // Don't go above the header
  $row.prev().before($row.get(0));
});

$('.table-down').click(function () {
  var $row = $(this).parents('tr');
  $row.next().after($row.get(0));
});

// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;

$BTN.click(function () {
  var $rows = $TABLE.find('tr:not(:hidden)');
  var headers = [];
  var data = [];
  
  // Get the headers (add special header logic here)
  $($rows.shift()).find('th:not(:empty)').each(function () {
    headers.push($(this).text().toLowerCase());
  });
  
  // Turn all existing rows into a loopable array
  $rows.each(function () {
    var $td = $(this).find('td');
    var h = {};
    
    // Use the headers from earlier to name our hash keys
    headers.forEach(function (header, i) {
      h[header] = $td.eq(i).text();   
    });
    
    data.push(h);
  });
  
  // Output the result
  $EXPORT.text(JSON.stringify(data));
});