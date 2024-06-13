/* eslint-disable */
document.addEventListener("DOMContentLoaded", function () {

    const properties = [
        'name',
        'gender',
        'neutered',
        'breed',
        'purebred',
        'weight',
        'age'
    ];

    properties.forEach(function (val, i) {

        var orderClass = '';

        document.getElementById(val).addEventListener('click', function (e) {
            e.preventDefault();
            const activeLinks = document.querySelectorAll('.filter__link.filter__link--active');
            activeLinks.forEach(function (link) {
                if (link !== this) {
                    link.classList.remove('filter__link--active');
                }
            }.bind(this));
            this.classList.toggle('filter__link--active');
            const allLinks = document.querySelectorAll('.filter__link');
            allLinks.forEach(function (link) {
                link.classList.remove('asc', 'desc');
            });

            if (orderClass === 'desc' || orderClass === '') {
                this.classList.add('asc');
                orderClass = 'asc';
            } else {
                this.classList.add('desc');
                orderClass = 'desc';
            }

            const parent = this.closest('.header__item');
            const index = Array.from(document.querySelectorAll('.header__item')).indexOf(parent);
            const table = document.querySelector('.table-content');
            const rows = Array.from(table.querySelectorAll('.table-row'));

            const isSelected = this.classList.contains('filter__link--active');
            const isNumber = this.classList.contains('filter__link--float-number');
            const isAge = this.classList.contains('filter__link--age');

            rows.sort(function (a, b) {
                let x = a.querySelector('.table-data').eq(index).textContent;
                let y = b.querySelector('.table-data').eq(index).textContent;

                if (isNumber) {
                    if (isSelected) {
                        return x - y;
                    } else {
                        return y - x;
                    }
                } else {
                    if (isSelected) {
                        if (x < y) return -1;
                        if (x > y) return 1;
                        return 0;
                    } else {
                        if (x > y) return -1;
                        if (x < y) return 1;
                        return 0;
                    }
                }
            });

            rows.forEach(function (row) {
                table.appendChild(row);
            });

            return false;
        });

    })
})