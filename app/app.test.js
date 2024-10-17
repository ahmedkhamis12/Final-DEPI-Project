const request = require('supertest');
const app = require('./server');  // Adjust the path to your 'server.js'

// Mock MongoClient from 'mongodb'
const { MongoClient } = require('mongodb');

jest.mock('mongodb', () => {
  const mClient = {
    db: jest.fn().mockReturnValue({
      collection: jest.fn().mockReturnValue({
        findOne: jest.fn().mockResolvedValue({ userid: 1, name: "Messi" }),
        updateOne: jest.fn().mockResolvedValue({ acknowledged: true, modifiedCount: 1 })
      })
    }),
    close: jest.fn()
  };

  return {
    MongoClient: {
      connect: jest.fn((url, options, callback) => {
        callback(null, mClient);
      })
    }
  };
});

// Increase timeout for the tests if needed
jest.setTimeout(10000);  // Set timeout to 10 seconds

// Test for fetching profile
// describe('GET /get-profile', () => {
//   it('should return a user profile', async () => {
//     const res = await request(app).get('/get-profile');
//     expect(res.statusCode).toEqual(200);
//     expect(res.body).toHaveProperty('userid', 1);
//     expect(res.body).toHaveProperty('name', 'Messi');
//   });
// });

// Test for updating profile
describe('POST /update-profile', () => {
  it('should update a user profile', async () => {
    const res = await request(app)
      .post('/update-profile')
      .send({ name: 'Ronaldo' });
      
    expect(res.statusCode).toEqual(200);
    expect(res.body).toHaveProperty('name', 'Ronaldo');
  });
});