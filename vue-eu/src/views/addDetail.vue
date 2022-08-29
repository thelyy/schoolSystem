<template>
    <div>
      <label class="navy-lbl">商品名称:</label>
      <input type="text" id="goods_search_gname" class="navy_ipt200">
      <div >
        <li class="myli">
          <label>商品详细:</label>
        </li>
      </div>

      <textarea id="editor" rows="10" cols="80"></textarea>
    </div>

</template>

<script>
  import CKEDITOR from "CKEDITOR"

  export default {
  name: "addDetail",
  data(){
    return{
      editor: '',
      editorData: '<p>Content of the editor.</p>',
      editorConfig: {
        // The configuration of the editor.
      }
    }
  },
  mounted() {

    CKEDITOR.replace('editor', {
      height: 300
    })

    CKEDITOR.on( 'paste', function( evt ) {
        var dataObj = evt.data,
        data = dataObj.dataValue,
        dataTransfer = dataObj.dataTransfer;

        console.log(data)
      // If data empty check for image content inside data transfer. https://dev.ckeditor.com/ticket/16705
      // Allow both dragging and dropping and pasting images as base64 (#4681).
      if ( !data && isFileData( evt, dataTransfer ) ) {
        var file = dataTransfer.getFile( 0 );
        if ( CKEDITOR.tools.indexOf( supportedImageTypes, file.type ) != -1 ) {
          var fileReader = new FileReader();

          // Convert image file to img tag with base64 image.
          fileReader.addEventListener( 'load', function() {
            evt.data.dataValue = '<img src="' + fileReader.result + '" />';
            editor.fire( 'paste', evt.data );
          }, false );

          // Proceed with normal flow if reading file was aborted.
          fileReader.addEventListener( 'abort', function() {
            // (#4681)
            setCustomIEEventAttribute( evt );
            editor.fire( 'paste', evt.data );
          }, false );

          // Proceed with normal flow if reading file failed.
          fileReader.addEventListener( 'error', function() {
            // (#4681)
            setCustomIEEventAttribute( evt );
            editor.fire( 'paste', evt.data );
          }, false );

          fileReader.readAsDataURL( file );

          latestId = dataObj.dataTransfer.id;

          evt.stop();
        }
      }
    }, null, null, 1 );

  },
  watch: {

  }
}
</script>

<style scoped>

</style>
