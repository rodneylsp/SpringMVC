//jquery
$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); //botao que disparou o evento
    var idTitulo = button.data('id');
    var descricaoTitulo = button.data('descricao');

    var modal = $(this);
    var form = modal.find('form');
    var action = form.attr('action');
    if(!action.endsWith('/')){
        action += '/';
    }
    form.attr('action', action + idTitulo);

    modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
});
