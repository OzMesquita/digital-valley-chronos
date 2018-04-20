/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function () {
    'use strict';
    window.addEventListener('load', function () {
        var form = document.getElementById('needs-validation');
        form.addEventListener('submit', function (event) {
            if ((form.checkValidity() === false)) {
                event.preventDefault();
                event.stopPropagation();
                $(".modal").modal('hide');
            }
            form.classList.add('was-validated');
        }, false);
    }, false);
})();
