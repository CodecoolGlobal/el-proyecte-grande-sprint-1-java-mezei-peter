import React, { useState, useEffect } from "react";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Modal from "../Modal/Modal.jsx";

function UserProfile(props) {
    const [isOpen, setIsOpen] = useState(false);

    return (
      <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
          <h1>user profile here</h1>
          <Button onClick={() => setIsOpen(true)}>open modal</Button>
          <Modal open={isOpen} onClose={() => setIsOpen(false)}>
              <div>
                  <table>
                      <tbody>
                          <th>Company</th>
                          <th>Contact</th>
                          <th>Country</th>
                      </tbody>
                      <tbody>
                          <td>Alfreds Futterkiste</td>
                          <td>Maria Anders</td>
                          <td>Germany</td>
                      </tbody>
                      <tbody>
                          <td>Centro comercial Moctezuma</td>
                          <td>Francisco Chang</td>
                          <td>Mexico</td>
                      </tbody>
                      <tbody>
                          <td>Ernst Handel</td>
                          <td>Roland Mendel</td>
                          <td>Austria</td>
                      </tbody>
                      <tbody>
                          <td>Island Trading</td>
                          <td>Helen Bennett</td>
                          <td>UK</td>
                      </tbody>
                      <tbody>
                          <td>Laughing Bacchus Winecellars</td>
                          <td>Yoshi Tannamuri</td>
                          <td>Canada</td>
                      </tbody>
                      <tbody>
                          <td>Magazzini Alimentari Riuniti</td>
                          <td>Giovanni Rovelli</td>
                          <td>Italy</td>
                      </tbody>
                  </table>
              </div>
          </Modal>
      </div>
    );
}

export default UserProfile;