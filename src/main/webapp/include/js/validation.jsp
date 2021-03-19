<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>

    $(document).ready(function () {
        $('form').on('submit', function (e) {
            if (!validate()) {
                e.preventDefault();
            }
            return true;
        })
    });

    function validate() {
        let valid = true
        $(".required").each(function (i, data) {
            let val = $(data).val();
            if (val == '') {
                valid = false;
                alert("Поле \"" + $(data).attr('title') + "\" Не должно быть пустым");
            }
        });

        return valid;
    }


</script>