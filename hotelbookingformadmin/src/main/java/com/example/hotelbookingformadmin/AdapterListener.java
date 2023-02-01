package com.example.hotelbookingformadmin;

public interface AdapterListener {
    void OnUpdate(RoomData roomData);
    void OnDelete(int id, int pos);
}
