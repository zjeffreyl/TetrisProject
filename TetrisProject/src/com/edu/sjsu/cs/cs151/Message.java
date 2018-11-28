package com.edu.sjsu.cs.cs151;

import java.io.Serializable;

public class Message implements Serializable
{
    static class RotateMessage extends Message{}
    static class LeftMessage extends Message{}
    static class RightMessage extends Message{}
    static class SlowDropMessage extends Message{}
    static class FastDropMessage extends Message{}
}
