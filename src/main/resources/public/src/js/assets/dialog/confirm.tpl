    <div class="modal fade" id="{{id}}" tabindex="-1" role="dialog" aria-labelledby="Title{{id}}" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="Title{{id}}">
                    {{{title}}}
                </h4>
            </div>
            <div class="modal-body" id="confirmBody{{id}}">
                {{{content}}}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="btnConfirm{{id}}">
                    确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btnCancel{{id}}">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>