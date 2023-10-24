/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    // Định nghĩa các tên vai trò
    public static final Map<Integer, String> roleNames = new HashMap<>();
    static {
        roleNames.put(1, "Admin");
        roleNames.put(2, "User");
        roleNames.put(3, "Mentee");
        roleNames.put(4, "Mentor");
    }

    // Định nghĩa các trạng thái của vai trò
    public static final Map<Integer, String> roleStatus = new HashMap<>();
    static {
        roleStatus.put(1, "ACTIVE");
        roleStatus.put(0, "INACTIVE");
    }

    // Các hằng số khác có thể được định nghĩa ở đây
}

