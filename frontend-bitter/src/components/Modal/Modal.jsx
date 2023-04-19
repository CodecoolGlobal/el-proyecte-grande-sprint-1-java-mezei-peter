import React from 'react'
import Button from "@mui/material/Button";

const MODAL_STYLES = {
    position: 'fixed',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    backgroundColor: '#FFF',
    padding: '50px',
    zIndex: 1000,
    display: "flex",
    flexDirection: "column"
};

const OVERLAY_STYLES = {
    width: "100%",
    height: "100%",
    position: 'fixed',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    backgroundColor: 'rgba(0, 0, 0, .7)',
    zIndex: 1000
};

export default function Modal({ open, children, onClose, overlay_style, modal_style }) {
    if (!open) return null;

    return (
        <>
            <div id="overlay" style={overlay_style ?? OVERLAY_STYLES} onClick={onClose}/>
            <div id="modal" style={modal_style ?? MODAL_STYLES}>
                <Button onClick={onClose}>Close</Button>
                {children}
            </div>
        </>
    );
}