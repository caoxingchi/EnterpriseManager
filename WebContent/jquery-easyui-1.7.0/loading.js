var pc;
//不要放在$(function(){});中
$.parser.onComplete = function () {
    if (pc) clearTimeout(pc);
    pc = setTimeout(closes,500);
}

function closes() {
    $('#loading').fadeOut('normal', function () {
        $(this).remove();
    });
}