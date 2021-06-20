import React from 'react'

export const FormatPrice = (price) => {
    return formatter.format(price)
}


export default class Utils {

    constructor() {

    }

    static formatter = new Intl.NumberFormat('pl-PL', {
        style: 'currency',
        currency: 'PLN',
    });

    static formatPrice(price) {
        return this.formatter.format(price)
    }
}
