package com.edu.sjsu.cs.cs151;

import com.edu.sjsu.cs.cs151.Views.Message;

public interface Valve
{
    public ValveResponse execute(Message message);
}
