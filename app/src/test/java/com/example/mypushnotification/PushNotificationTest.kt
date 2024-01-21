package com.example.mypushnotification

import android.app.NotificationManager
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MyFirebaseMessagingServiceTest {
    private lateinit var myFirebaseMessagingService: MyFirebaseMessagingService

    @Before
    fun setUp() {
        myFirebaseMessagingService = MyFirebaseMessagingService()
    }

    @Test
    fun `test generateNotification`() {
        val title = "Test Title"
        val message = "Test Message"

        // create mock NotificationManager
        val notificationManager = mock(NotificationManager::class.java)

        // set NotificationManager in MyFirebaseMessagingService
        myFirebaseMessagingService.notificationManager = notificationManager

        // call generateNotification method
        myFirebaseMessagingService.generateNotification(title, message)

        // verify that the notification was created and sent
        verify(notificationManager).notify(0, myFirebaseMessagingService.builder.build())

        // verify that the title and message were set in the RemoteViews
        val remoteViews = myFirebaseMessagingService.getRemoteView(title, message)
        assertEquals(title, remoteViews.getTextViewText(R.id.title))
        assertEquals(message, remoteViews.getTextViewText(R.id.message))
    }
}