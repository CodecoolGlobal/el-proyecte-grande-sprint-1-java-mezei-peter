

function Modal({content, isOpen, setIsOpen}) {
    let modalDisplay = isOpen ? "block" : "none";
    let closeStyleDef = {
        color: "#aaaaaa",
        float: "right",
        fontSize: "28px",
        fontWeight: "bold"
    };
    let closeStyleHover = {
        float: "right",
        fontSize: "28px",
        fontWeight: "bold",
        color: "#000",
        textDecoration: "none",
        cursor: "pointer"
    };
    let closeStyle = closeStyleDef;

    return (
        <div
            className="modal"
            style={{
                display: modalDisplay, /* Hidden by default */
                position: "fixed", /* Stay in place */
                zIndex: "1", /* Sit on top */
                left: "0",
                top: "0",
                width: "100%", /* Full width */
                height: "100%", /* Full height */
                overflow: "auto", /* Enable scroll if needed */
            }}
            onClick = {() => {
                setIsOpen(false);
                console.log("modal");
                console.log(isOpen);
            }}
        >
            <div
                className="modal-content"
                style={{
                    backgroundColor: "#fefefe",
                    margin: "auto",
                    padding: "20px",
                    border: "1px solid #888",
                    width: "80%"
                }}
                >
                <span
                    className="close"
                    style={closeStyle}
                    onMouseEnter={() => closeStyle = closeStyleHover}
                    onMouseLeave={() => closeStyle = closeStyleDef}
                    onClick = {() => {
                        setIsOpen(false);
                        console.log("X");
                    }}
                >&times;</span>
                { content }
            </div>
        </div>
    );
}

export default Modal;