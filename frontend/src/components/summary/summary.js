import './summary.css'
import React from 'react'
export const Summary = ({ summaryValue }) => {
    return (
        <div className="summary">
            <p>Podsumowanie: {summaryValue} </p>
        </div>
    )
}
