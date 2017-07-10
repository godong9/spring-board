/**
 * Mocking client-server processing
 */
const posts = [
  { id: 1,
    title: '이제 막차떠납니다.',
    content: '내용',
    view_count: 100,
    comment_count: 100,
    post_like_count: 100,
    blocked: false,
    created_at: new Date(),
    updated_at: new Date(),
    user: { id: 2, name: 'gd' } },
  { id: 2,
    title: '화이팅합시다',
    content: '내용',
    view_count: 100,
    comment_count: 10,
    post_like_count: 5,
    blocked: false,
    created_at: new Date(),
    updated_at: new Date(),
    user: { id: 2, name: 'jaipam' } },
  { id: 2,
    title: '유저10명',
    content: '내용',
    view_count: 200,
    comment_count: 20,
    post_like_count: 50,
    blocked: false,
    created_at: new Date(),
    updated_at: new Date(),
    user: { id: 2, name: 'paranpi' } },
];

export default {
  getPosts(cb) {
    setTimeout(() => cb(posts), 100);
  },
};
