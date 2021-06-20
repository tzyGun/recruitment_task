import './product-item.css'
import Utils from '../../utils/format-price'
import React from 'react'
import { Counter } from '../counter/counter'
export const ProductItem = ({ item }) => {
    return (
        <li className="row product-item">
            <Counter item={item} />
            <span className="product-name">{item.name}</span>
            <span className="product-price">{Utils.formatPrice(item.price)}</span>
        </li>
    )
}
