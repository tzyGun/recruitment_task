import './summary.css'
import React from 'react'
import Utils from '../../utils/format-price'
export const Summary = ({ summary }) => {
    return (
        <div className="summary">
            <p>Podsumowanie: {Utils.formatPrice(summary)} </p>
        </div>
    )
}
