package org.firstinspires.ftc.teamcode.control;

import com.qualcomm.robotcore.util.ElapsedTime;

public interface Controller<T> {
    ElapsedTime timer = new ElapsedTime();

    T update(T current, T setpoint);
    void reset();
}
