package com.edu.sjsu.cs.cs151;

import sun.plugin2.message.Message;

public interface Valve
{
    public ValveResponse execute(Message message);
}
