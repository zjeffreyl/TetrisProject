package com.edu.sjsu.cs.cs151.Views;

import java.io.Serializable;

public class Message implements Serializable
{
    public static class NewGameMessage extends Message{}
    public static class RotateMessage extends Message{}
    public static class LeftMessage extends Message{}
    public static class RightMessage extends Message{}
    public static class SlowDropMessage extends Message{}
    public static class FastDropMessage extends Message{}
}
